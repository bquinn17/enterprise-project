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
import { MenuItem } from 'material-ui/Menu'
import TextField from 'material-ui/TextField'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import styles from './WholesaleOrderPageStyles'

//
import getRepsFromRegion from '../../stubbed-data/dataFromHR'
import getWholeSaleAccounts from '../../stubbed-data/dataFromFutureReleases'
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
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class WholesaleOrderPage extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      region: "",
      reps: [],
      repId: null,
      wholesaleAccountId: null,
      numFlex: 0,
      numStyle: 0,
      numActive: 0
    }
    this.handleRegionSelect = this.handleRegionSelect.bind(this)
    this.handleRepSelect = this.handleRepSelect.bind(this)
    this.handleWholesaleSelect = this.handleWholesaleSelect.bind(this)
  }

  handleRegionSelect = event => {
    const reps = getRepsFromRegion(event.target.value)
    this.setState({ region: event.target.value })
  }

  handleRepSelect = event => {
    this.setState({
      repId: event.target.value
    })
  }

  handleWholesaleSelect = event => {
    this.setState({
      wholesaleAccountId: event.target.value
    })
  }

  handleProductOneNum = event => {
    this.setState({
      numFlex: event.target.value
    })
  }

  handleProductTwoNum = event => {
    this.setState({
      numStyle: event.target.value
    })
  }

  handleProductThreeNum = event => {
    this.setState({
      numActive: event.target.value
    })
  }

  handleSubmit(){

    // Find the wholesale data object
    var wholesalers = getWholeSaleAccounts()
    var wholesale = wholesalers.find(function(wholesaleAcc) {
                      return wholesaleAcc.email === this.state.wholesaleAccountId
                    }, this)

    // Find the sales rep data object
    var reps = getRepsFromRegion(this.state.region)
    var rep = reps.find(function(repObj) {
                return repObj.id === this.state.repId
    }, this)

    // Create the post request
    const request = {
      "orderMap": [
          {
            "model": "Kenn-U-Active",
            "quantity": this.state.numActive
          },
          {
            "model": "Kenn-U-Flex",
            "quantity": this.state.numFlex
          },
          {
            "model": "Kenn-U-Style",
            "quantity": this.state.numStyle
          }
          // { "Kenn-U-Flex": this.state.numFlex },
          // { "Kenn-U-Style": this.state.numStyle },
        ],
      "status": "placed",
      "wholesaleAccount": {
        "email": wholesale.email,
        "shippingAddress": wholesale.shippingAddress,
        "shippingState": wholesale.shippingState,
        "shippingTown": wholesale.shippingTown,
        "shippingZip": wholesale.shippingZip
      },
      "salesRep": {
        "firstName": rep.lastName,
        "lastName": rep.firstName
      },
      "region": this.state.region
    }

    // POST the request
    axios.post('/api/orders/wholesale/new',
      request
    ).then(function(response) {
      alert("success!" + response)
    }).catch(function(error) {
      alert("error!" + error)
    })
  }

  render() {
    const { classes } = this.props

    // Only render the repsDropDown if a region has been selected
    const repsDropDown = this.state.region === "" ? null : (
      <WholesaleOrderRepDropDown
        selectedValue={ this.state.repId }
        onSelect={ this.handleRepSelect }
        region={ this.state.region }
      />
    )

    // Only render the wholeSaleDropDown if a rep has been selected
    const wholeSaleAccountDropDown = this.state.repId === null ? null : (
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
        <img
         className={ classes.productImg }
         src={flexImg}
        />
        <TextField
          label="Kenn-U-Flex"
          value={ this.state.numFlex }
          type="number"
          onChange={ this.handleProductOneNum }
          className={ classes.productQuant }
        />
        <br />
        <img
         className={ classes.productImg }
         src={ styleImg }
        />
        <TextField
          label="Kenn-U-Style"
          value={ this.state.numStyle }
          type="number"
          onChange={ this.handleProductTwoNum }
          className={ classes.productQuant }
        />
        <br />
        <img
         className={ classes.productImg }
         src={activeImg}
        />
        <TextField
          label="Kenn-U-Active"
          value={ this.state.numActive }
          type="number"
          onChange={ this.handleProductThreeNum }
          className={ classes.productQuant }
        />
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
          <Link to="/">
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
