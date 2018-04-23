import React from 'react'
//
import axios from 'axios'
//
import Button from 'material-ui/Button'
import Card, { CardContent } from 'material-ui/Card'
import IconButton from 'material-ui/IconButton'
import { withStyles } from 'material-ui/styles'
import Typography from 'material-ui/Typography'
import TextField from 'material-ui/Input'
import GridList, { GridListTile } from 'material-ui/GridList'
//
import { Redirect } from 'react-router'
//
import { Link } from 'react-router-dom'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import styles from './checkoutPageStyles'
//
import OrderPlaced from './OrderPlaced'

/**
 * Note valid_credit_card was found from online source:
 * https://gist.github.com/DiegoSalazar/4075533
 * as it is a valid Luhn Algorthm checker.
 *
 * The sales erp silo takes no credit for this code
 */

// takes the form field value and returns true on valid number
function valid_credit_card(value) {
  // accept only digits, dashes or spaces
	if (/[^0-9-\s]+/.test(value)) return false;

	// The Luhn Algorithm. It's so pretty.
	var nCheck = 0, nDigit = 0, bEven = false;
	value = value.replace(/\D/g, "");

	for (var n = value.length - 1; n >= 0; n--) {
		var cDigit = value.charAt(n),
			  nDigit = parseInt(cDigit, 10);

		if (bEven) {
			if ((nDigit *= 2) > 9) nDigit -= 9;
		}

		nCheck += nDigit;
		bEven = !bEven;
	}

	return (nCheck % 10) == 0;
}


class CheckoutPage extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      email: '',
      shippingState: '',
      shippingStreetAddress :'',
      shippingTown: '',
      shippingZip: '',
      creditCard: '',
      redirectToFinalize: false
    }
    this.handleFormChange = this.handleFormChange.bind(this)
    this.shouldDisableSubmit = this.shouldDisableSubmit.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleFormChange = prop => event => {
    this.setState({ [prop]: event.target.value} )
  }

  shouldDisableSubmit() {
    return (
      this.state.email.length <= 0 || this.state.shippingState.length <= 0 ||
      this.state.shippingStreetAddress.length <= 0 ||
      this.state.shippingTown.length <= 0 || this.state.shippingZip.length <= 0
    )
  }

  handleSubmit() {
    var productsArr = []
    var totalPrice = 0
    for (var model in this.props.cartData) {
      var item = this.props.cartData[model]
      totalPrice += item.quantity * item.cost
      if(item.quantity > 0) {
        var itemCount = item.quantity
        while(itemCount > 0) {
          productsArr.push(
            {
              "model": item.modelName,
              "refurbished": false,
              "priceSoldAt": item.cost
            }
          )
          itemCount -= 1
        }
      }
    }

    const request = {
      "customerEmail": this.state.email,
      "customerShippingState": this.state.shippingState,
      "customerShippingStreetAddress": this.state.shippingStreetAddress,
      "customerShippingTown": this.state.shippingTown,
      "customerShippingZip": this.state.shippingZip,
      "products": productsArr,
      "totalPrice": totalPrice,
      "status": "fullfilled"
    }
    console.log(request)
    axios.post('/api/orders/retail/new',
      request
    ).then(function(response) {

      this.setState({
        redirectToFinalize: true
      })
      this.props.clearCart()
    }.bind(this)).catch(function(error) {
      alert("error! " + error)
    })
  }
  render() {
    if(this.state.redirectToFinalize) {
      return <OrderPlaced />
    }
    const { classes } = this.props

    var creditCardMessage = valid_credit_card(this.state.creditCard) ? null : (
      <Typography
        type="subheading"
        variant="title"
        align="center"
        className={classes.itemText}
      >
        Invalid credit card. Must follow the Luhn Algorithm
      </Typography>
    )
    var itemsList = []
    var totalCost = 0

    for (var model in this.props.cartData) {
      var item = this.props.cartData[model]
      if(item.quantity > 0) {
        totalCost += item.quantity * item.cost
        itemsList.push(

            <GridList cellHeight={50} className={classes.gridList} cols={12}>
              <GridListTile className={classes.gridTile} cols={2}>
                <Typography
                  type="subheading"
                  variant="title"
                  align="center"
                  className={classes.itemText}
                >
                  {item.quantity} x
                </Typography>
              </GridListTile>
              <GridListTile cellHeight={30} cols={2} className={classes.gridTile}>
                <img className={classes.productImg} src={item.imgSrc} />
              </GridListTile>
              <GridListTile cols={8} className={classes.gridTile}>
                <Typography
                  type="subheading"
                  variant="title"
                  align="center"
                  className={classes.itemText}
                >
                  <div className={classes.priceTile}>
                    {item.modelName + " = " + (item.quantity * item.cost).toFixed(2) + " "}
                  </div>
                </Typography>
              </GridListTile>
            </GridList>
        )
      }
    }

    var displayTotal = totalCost.toFixed(2)
    return(
      <Card className={ classes.card }>
        <Link to="/store/catalog">
          <IconButton>
            <ArrowBack />
          </IconButton>
        </Link>
        <Typography
          type="subheading"
          variant="display2"
          align="center"
          gutterBottom
        >
          Order Checkout
        </Typography>
        <Card className={ classes.itemCard }>
          <CardContent>
            {itemsList}
            <Typography
              type="subheading"
              variant="title"
              align="center"
              className={classes.totalCost}
            >
              <span className={classes.priceTile}>
                Total: ${displayTotal}
              </span>
            </Typography>
          </CardContent>
        </Card>
        <CardContent>
          <Typography
            type="subheading"
            variant="display1"
            align="center"
          >
            Shipping & Billing Info:
          </Typography>
          <Card className={ classes.itemCard }>
          <TextField
            required="true"
            placeholder="Email"
            className={ classes.textbox }
            onChange = { this.handleFormChange('email') }
          />
          <TextField
            required="true"
            placeholder="Street Address"
            className={ classes.textbox }
            onChange = { this.handleFormChange('shippingStreetAddress') }
          />
          <TextField
            required="true"
            placeholder="Town/City"
            className={ classes.textbox }
            onChange = { this.handleFormChange('shippingTown') }
          />
          <TextField
            required="true"
            placeholder="State"
            className={ classes.textbox }
            onChange = { this.handleFormChange('shippingState') }
          />
          <TextField
            required="true"
            placeholder="Zip Code"
            className={ classes.finalTextBox }
            onChange = { this.handleFormChange('shippingZip') }
          />
          <TextField
            required="true"
            placeholder="Credit Card Number"
            className={ classes.finalTextBox }
            onChange= {this.handleFormChange('creditCard')}
          />
          {creditCardMessage}
          <Button
            disabled={ this.shouldDisableSubmit() ||
               (!valid_credit_card(this.state.creditCard) ||
               this.state.creditCard.length == 0)}
            className={ classes.submitButton }
            onClick={ this.handleSubmit.bind(this) }
          >
            Submit Order
          </Button>
          </Card>
        </CardContent>
      </Card>
    )
  }
}

export default withStyles(styles)(CheckoutPage)
