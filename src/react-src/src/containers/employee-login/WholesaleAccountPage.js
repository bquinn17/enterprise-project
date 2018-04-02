import React from 'react'
//
import axios from 'axios'
//
import { Link } from 'react-router-dom'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import { FormControl, FormHelperText } from 'material-ui/Form'
import IconButton from 'material-ui/IconButton'
import Input, { InputLabel } from 'material-ui/Input'
import TextField from 'material-ui/TextField'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import styles from './wholesaleAccountPageStyles'
//
import flexImg from '../../flex.jpg'
import styleImg from '../../style.jpeg'
import activeImg from '../../active.jpg'

/**
 * WholesaleOrderPage is used to wrap and respond to the form
 * that Sales Rep Managers use to report and make a wholesale order.
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class WholesaleAccountPage extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [
        {
          model: "Kenn-U-Style",
          "price": 0.0
        },
        {
          model: "Kenn-U-Active",
          "price": 0.0
        },
        {
          model: "Kenn-U-Flex",
          "price": 0.0
        }
      ],
      accountName: '',
      email: '',
      shippingState: '',
      shippingStreetAddress :'',
      shippingTown: '',
      shippingZip :'',
      redirect: false
    }
    this.handleFormChange = this.handleFormChange.bind(this)
    this.shouldDisableSubmit = this.shouldDisableSubmit.bind(this)
    this.handlePriceChange = this.handlePriceChange.bind(this)
    this.getImgFromModel = this.getImgFromModel.bind(this)
    this.getProductPrice = this.getProductPrice.bind(this)
  }

  getImgFromModel(model) {
    switch(model){
      case "Kenn-U-Flex":
        return flexImg
      case "Kenn-U-Style":
        return styleImg
      case "Kenn-U-Active":
        return activeImg
    }
  }

  getProductPrice(modelName) {
    var productsArr = this.state.products.filter(e => e.model === modelName)
    if (productsArr.length > 0) {
      return productsArr[0].price
    }
  }

  shouldDisableSubmit() {
    return (
      this.state.email.length <= 0 || this.state.shippingState.length <= 0 ||
      this.state.shippingStreetAddress.length <= 0 ||
      this.state.shippingTown.length <= 0 ||
      this.state.accountName.length <= 0 ||
      this.state.shippingZip.length <= 0
    )
  }

  handleFormChange = prop => event => {
    this.setState({ [prop]: event.target.value} )
  }

  handlePriceChange = modelName => event => {
    var productsArr = this.state.products
    productsArr.forEach(function(product) {
      if(product.model === modelName){
        product.price = event.target.value
      }
    })
    this.setState({
       products: productsArr
    })
  }

  handleSubmit(){

    // Create the post request body
    var request = {
     "configuredPrice": this.state.products,
     "email": this.state.email,
     "name": this.state.accountName,
     "shippingAddress": this.state.shippingStreetAddress,
     "shippingState": this.state.shippingState,
     "shippingTown": this.state.shippingTown,
     "shippingZip": this.state.shippingZip
    }
    //POST the request
    axios.post('/api/wholesale/account/new',
      request
    ).then(function(response) {
      alert("success!" + response)
    }).catch(function(error) {
      alert("error!" + error)
    })
    console.log(request)
  }

  render() {
    const { classes } = this.props

    const productList = this.state.products.map(function(product){
      return(
        <span>
          <img
           className={ classes.productImg }
           src={this.getImgFromModel(product.model) }
          />
          <TextField
            label={ product.model + " Price" }
            value={ this.getProductPrice(product.model) }
            type="number"
            onChange={ this.handlePriceChange(product.model) }
            className={ classes.productQuant }
          />
          <br />
        </span>
      )
    }, this)
    return (
      <div>
        <Typography
          type="subheading"
          variant="display1"
          align="center"
          className={ classes.title }
        >
          Create a Wholesale Account
        </Typography>
        <Card className={ classes.card }>
          <Link to="/employee/dashboard">
            <IconButton>
              <ArrowBack />
            </IconButton>
          </Link>
          <TextField
            required="true"
            placeholder="Account Name"
            className={ classes.textbox }
            onChange = { this.handleFormChange('accountName') }
          />
          <div className= {classes.productWrapper }>
            {productList}
          </div>
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
      </div>
    )
  }
}

export default withStyles(styles)(WholesaleAccountPage)
