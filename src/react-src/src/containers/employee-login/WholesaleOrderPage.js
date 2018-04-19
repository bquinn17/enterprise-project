import React from 'react'
//
import axios from 'axios'
//
import { Link } from 'react-router-dom'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import { FormControl } from 'material-ui/Form'
import IconButton from 'material-ui/IconButton'
import TextField from 'material-ui/TextField'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import styles from './WholesaleOrderPageStyles'
import WholesaleOrderAccountDropDown from './WholesaleOrderAccountDropDown'
import WholesaleOrderRegionDropDown from './WholesaleOrderRegionDropDown'
import WholesaleOrderRepDropDown from './WholesaleOrderRepDropDown'
//
import flexImg from '../../flex.jpg'
import styleImg from '../../style.jpeg'
import activeImg from '../../active.jpg'

/**
 * WholesaleOrderPage is used to wrap and respond to the form
 * that Sales Rep Managers use to report and make a wholesale order.
 *
 * Author: Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */
class WholesaleOrderPage extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      region: "",
      reps: [],
      productsArr: [],
      selectedRep: null,
      wholesaleAccountId: null,
    }
    this.handleRegionSelect = this.handleRegionSelect.bind(this)
    this.handleRepSelect = this.handleRepSelect.bind(this)
    this.handleWholesaleSelect = this.handleWholesaleSelect.bind(this)
    this.getCost = this.getCost.bind(this)
    this.getImgFromModel = this.getImgFromModel.bind(this)
    this.increaseQuantity = this.increaseQuantity.bind(this)
    this.getTotalCost = this.getTotalCost.bind(this)
  }

  handleRegionSelect = event => {
    this.setState({ region: event.target.value,
       wholesaleAccountId: null,
        selectedRep: null })
  }

  handleRepSelect = event => {
    this.setState({
      selectedRep: event.target.value
    })
  }

  handleWholesaleSelect = event => {
    var productsArr = event.target.value.configuredPrice
    var modifiedArr = productsArr.map(function(product){
      return({
        model: product.model,
        price: product.price,
        quantity: 0
      })
    })
    this.setState({
      wholesaleAccountId: event.target.value,
      productsArr: modifiedArr
    })
  }
  handleSubmit(){

    // Create the orderMap part of the request
    var orderMap = this.state.productsArr.map(function(product){
      return({
        "model": product.model,
        "quantity": product.quantity
      })
    })

    // Create the post request
    const request = {
      "orderMap": orderMap,
      "salesRep": {
        "firstName": this.state.selectedRep.firstName,
        "lastName": this.state.selectedRep.lastName,
        "region": this.state.selectedRep.regionName.toLowerCase(),
        "employeeId": this.state.selectedRep.id
      },
      "totalPrice": this.getTotalCost(),
      "wholesaleAccount": this.state.wholesaleAccountId
    }

    //POST the request
    axios.post('/api/orders/wholesale/new',
      request
    ).then(function(response) {
      alert("success!" + response)
    }).catch(function(error) {
      alert("error!" + error)
      console.log(error)
    })
  }

  getCost(model){
    var arr = this.state.wholesaleAccountId.configuredPrice.filter(
      e => e.model === model)
    return arr[0].price
  }

  getImgFromModel(model){
    switch(model) {
      case "Kenn-U-Active":
        return activeImg
      case "Kenn-U-Style":
        return styleImg
      case "Kenn-U-Flex":
        return flexImg
    }
  }

  increaseQuantity(event) {
    const model = event.target.name
    var modifiedArr = this.state.productsArr.map(function(product){
      if(model === product.model){
        return({
          model: product.model,
          price: product.price,
          quantity: parseFloat(event.target.value)
        })
      }else{
        return({
          model: product.model,
          price: product.price,
          quantity: parseFloat(product.quantity)
        })
      }
    })
    this.setState({
      productsArr: modifiedArr
    })
  }

  getTotalCost() {
    var total = 0
    this.state.productsArr.forEach(function(product){
      total += (product.price * product.quantity)
    }, total)
    return total
  }

  render() {
    const { classes } = this.props

    // Only render the repsDropDown if a region has been selected
    const repsDropDown = this.state.region === "" ? null : (
      <WholesaleOrderRepDropDown
        selectedValue={ this.state.selectedRep }
        onSelect={ this.handleRepSelect }
        region={ this.state.region }
      />
    )

    // Only render the wholeSaleDropDown if a rep has been selected
    const wholeSaleAccountDropDown = this.state.selectedRep === null ? null : (
      <WholesaleOrderAccountDropDown
        selectedValue={ this.state.wholesaleAccountId }
        onSelect={ this.handleWholesaleSelect }
      />
    )

    // Only render a products form if the wholesale account has been selected
    const productsForm = this.state.wholesaleAccountId === null ? null : (
      <div>
        <Typography
          type="subheading"
          variant="display1"
        >
          Product purchases
        </Typography>
        {this.state.productsArr.map(function(cp){
          return(
            <span>
              <img
               className={ classes.productImg }
               src={this.getImgFromModel(cp.model)}
              />
              <TextField
                label={cp.model}
                value={ cp.quantity }
                type="number"
                name={cp.model}
                onChange={ this.increaseQuantity }
                className={ classes.productQuant }
              />
              { "x " + cp.price}
              <br />
            </span>
          )
        }, this)}
        <Typography
          type="subheading"
          variant="subheading"
          align="center"
          className={ classes.title }
        >
          Total Price: ${ this.getTotalCost().toFixed(2) }
        </Typography>
        <Button
          className={ classes.submitButton }
          onClick={ this.handleSubmit.bind(this) }
        >
          Submit
        </Button>
      </div>
    )
    return (
      <div>
        <Typography
          type="subheading"
          variant="display1"
          align="center"
          className={ classes.title }
        >
          Report a Wholesale Order
        </Typography>
        <Card className={ classes.card }>
          <Link to="/employee/dashboard">
            <IconButton>
              <ArrowBack />
            </IconButton>
          </Link>
          <div className= {classes.wrapper }>
            <form className={ classes.wholesaleOrderForm }>
              <FormControl>
                <WholesaleOrderRegionDropDown
                  selectedValue={ this.state.region }
                  handleSelect={ this.handleRegionSelect }
                />
                { repsDropDown }
                { wholeSaleAccountDropDown }
                { productsForm }
              </FormControl>
            </form>
          </div>
        </Card>
      </div>
    )
  }
}

export default withStyles(styles)(WholesaleOrderPage)
