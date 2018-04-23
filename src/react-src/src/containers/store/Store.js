import React from 'react'
//
import axios from 'axios'
//
import { Link } from 'react-router-dom'
//
import AppBar from 'material-ui/AppBar'
import IconButton from 'material-ui/IconButton'
import Menu, { MenuItem } from 'material-ui/Menu'
import { withStyles } from 'material-ui/styles'
import { Tab } from 'material-ui/Tabs'
import Toolbar from 'material-ui/Toolbar'
import Typography from 'material-ui/Typography'
//
import ShoppingCart from 'material-ui-icons/ShoppingCart'
//
import CatalogPage from './CatalogPage'
import ContactUs from './ContactUs'
import CheckoutPage from './CheckoutPage'
//
import styles from './StoreStyles'

/**
 * Store is meant to be an app-wrapper for the retail part of this ERP
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class Store extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      totalCost: 0,
      itemsInCart: [],
      menuAnchor: null
    }
    this.addItem = this.addItem.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.getCartData = this.getCartData.bind(this)
    this.clearCart = this.clearCart.bind(this)
  }

  // Remove the server-side injected CSS.
  componentDidMount () {
    const jssStyles = document.getElementById('jss-server-side')
    if (jssStyles && jssStyles.parentNode) {
      jssStyles.parentNode.removeChild(jssStyles)
    }
  }

  addItem(item) {
    var addedTotalCost = this.state.totalCost + item.cost
    var roundedTotalCost = addedTotalCost.toFixed(2)
    var newTotalCost = parseFloat(roundedTotalCost)
    this.state.itemsInCart.push(item)
    this.setState({
      totalCost: newTotalCost,
    })
  }

  clearCart() {
    this.setState({
      totalCost: 0,
      itemsInCart: []
    })
  }

  handleCartClick = event => {
    this.setState({
      menuAnchor: event.currentTarget
    })
  }

  handleCartClose = () => {
    this.setState({
      menuAnchor: null
    })
  }

  getCartData = () =>  {
    var cartData = [
    ]
    this.state.itemsInCart.forEach(function(item) {
      var modelsWithNameArr = cartData.filter(e => e.modelName === item.model)
      if (modelsWithNameArr.length > 0) {
        modelsWithNameArr[0].quantity = modelsWithNameArr[0].quantity + 1
      } else {
        cartData.push({
          cost: item.cost,
          quantity: 1,
          imgSrc: item.imgSrc,
          modelName: item.model
        })
      }
    })
    return cartData
  }

  handleSubmit() {

    var cartArr = []
    this.state.itemsInCart.forEach(function(item) {
      cartArr.push(
        {
          "model": item.model,
          "refurbished": "false",
        }
      )
    })

    const request = {
      "customerEmail":"suzy@email.com",
      "customerShippingState":"NY",
      "customerShippingStreetAddress":"12 Lomb Memorial Drive",
      "customerShippingTown": "Rochester",
      "customerShippingZip": "14623",
      "products": cartArr,
      "status": "fullfilled"
    }
    // const response = fetch('/api/retail-order/new');
    // const body = await response.json();

    axios.post('/api/orders/retail/new',
      request
    ).then(function(response) {
      alert("success!" + response)
    }).catch(function(error) {
      alert("error!" + error)
    })
  }

  render() {
    const { classes } = this.props

    /* Determine which child page to render */
    var pageToShow
    switch(this.props.storePage) {
      case 'catalog':
        pageToShow = <CatalogPage
                        addItem={ this.addItem }
                        isEmpty={ this.state.itemsInCart.length === 0 }
                        handleSubmit={ this.handleSubmit }
                     />
        break;
      case 'contact-us':
        pageToShow = <ContactUs />
        break;
      case 'checkout':
        pageToShow = <CheckoutPage cartData={ this.getCartData() } clearCart={ this.clearCart }/>
        break;
    }

    const itemsInCart = this.state.itemsInCart

    return (
      <div className={ classes.root }>
        <AppBar className={ classes.appBar } color="default" position="static">
          <Toolbar>
            <Link className={ classes.linkHome } to='/'>
              <Typography
                variant="title"
                color="inherit"
                className={ classes.flex }>
                Kenn-U-Ware
              </Typography>
            </Link>
            <Tab
              className={ classes.buttonRightStyle }
              component={ Link }
              to="/store/catalog"
              label="Catalog"
            />
            <a className={ classes.linkHome } href="https://web-customersupport.azurewebsites.net/">
              <Tab label="Contact Us"/>
            </a>
            <div className={ classes.cart }>
              <span
                className={ classes.totalCost }>
                ${ this.state.totalCost }
                </span>
              <IconButton>
                <ShoppingCart onClick={ this.handleCartClick }/>
              </IconButton>
              <Menu
                id="simple-menu"
                anchorEl={ this.state.menuAnchor }
                open={ Boolean(this.state.menuAnchor) }
                onClose={ this.handleCartClose }
                className={ classes.cartMenu }
              >
                { itemsInCart.map(item => (
                    <MenuItem value={ 1 }>
                      <img
                       className={ classes.productImg }
                       src={ item.imgSrc }
                      />
                    { item.model } ${ item.cost }
                    </MenuItem>
                ))}
              </Menu>
            </div>
          </Toolbar>
        </AppBar>
        { pageToShow }
      </div>
    )
  }
}

// Wrap the Component with styles
const StoreWithStyles = withStyles(styles)(Store)

export default (StoreWithStyles)
