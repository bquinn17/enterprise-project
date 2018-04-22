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
import { Link } from 'react-router-dom'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import styles from './checkoutPageStyles'

class CheckoutPage extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      email: '',
      shippingState: '',
      shippingStreetAddress :'',
      shippingTown: '',
      shippingZip :''
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
      alert("success! " + response)
    }).catch(function(error) {
      alert("error! " + error)
    })
  }
  render() {
    const { classes } = this.props
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
            Shipping Info:
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
          <Button
            disabled={ this.shouldDisableSubmit() }
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
