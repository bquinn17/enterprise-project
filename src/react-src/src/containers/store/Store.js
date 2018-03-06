import React, { PureComponent } from 'react'
//
import { withRouteData, Link } from 'react-static'
//
import { hot } from 'react-hot-loader'
//
import AppBar from 'material-ui/AppBar'
import Avatar from 'material-ui/Avatar'
import Icon from 'material-ui/Icon'
import IconButton from 'material-ui/IconButton'
import { withStyles } from 'material-ui/styles'
import Tabs, { Tab } from 'material-ui/Tabs'
import Toolbar from 'material-ui/Toolbar'
import Typography from 'material-ui/Typography'
//
import ShoppingCart from 'material-ui-icons/ShoppingCart'
//
import CatalogPage from './CatalogPage'
import ContactUs from './ContactUs'
import logoImg from '../../logo.jpg'
import styles from './StoreStyles'

/**
 * Store is meant to be an app-wrapper for the retail part of this ERP
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class Store extends PureComponent {

  // Remove the server-side injected CSS.
  componentDidMount () {
    const jssStyles = document.getElementById('jss-server-side')
    if (jssStyles && jssStyles.parentNode) {
      jssStyles.parentNode.removeChild(jssStyles)
    }
  }

  render() {
    const { classes } = this.props

    /* Determine which child page to render */
    var pageToShow
    switch(this.props.data.storePage) {
      case 'catalog':
        pageToShow = <CatalogPage />
        break;
      case 'contact-us':
        pageToShow = <ContactUs />
        break;
    }

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
            <Tab component={ Link } to="/store/contact-us" label="Contact Us" />
            <div className={ classes.cart }>
              <IconButton>
                <ShoppingCart />
              </IconButton>
            </div>
          </Toolbar>
        </AppBar>
        { pageToShow }
      </div>
    )
  }
}

// Wrap with Component with data
const withData = withRouteData(Store)
// Wrap the Component with styles
const StoreWithStyles = withStyles(styles)(withData)

export default hot(module)(StoreWithStyles)
