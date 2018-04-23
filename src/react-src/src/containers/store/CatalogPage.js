import React from 'react'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import GridList from 'material-ui/GridList'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import { Redirect } from 'react-router'
//
import styles from './catalogPageStyles'
//
import flexImg from '../../flex.jpg'
import styleImg from '../../style.jpeg'
import activeImg from '../../active.jpg'
import comingSoonImg from '../../coming-soon.jpg'
//
import CatalogItem from './CatalogItem'
/**
 * CatalogPage is where users can see the range of products KennUWare offers
 * They must select one to configure sizing and to purchase
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class CatalogPage extends React.Component {
  constructor(props){
    super(props)

    this.state = { products: [],
      redirect: false,
      loading: false
    }
    this.getImg = this.getImg.bind(this)
    this.getPrice = this.getPrice.bind(this)
    this.routeToCheckout = this.routeToCheckout.bind(this)
  }

  getImg(model) {
    switch(model) {
      case "Kenn-U-Active":
        return activeImg
      case "Kenn-U-Style":
        return styleImg
      case "Kenn-U-Flex":
        return flexImg
      default:
        return comingSoonImg
    }
  }

  getPrice(model) {
    switch(model) {
      case "Kenn-U-Active":
        return 69.99
      case "Kenn-U-Style":
        return 129.99
      case "Kenn-U-Flex":
        return 49.99
    }
  }

  routeToCheckout() {
    this.setState({redirect: true})
  }

  componentDidMount() {
    this.setState({
      loading: true
    })
    fetch("/api/mocked/inventory/products")
      .then(response => response.json())
      .then(data => this.setState({ products: data, loading: false }))
      .catch(error => console.log("error!!! " + error));
  }
  render() {
    const { classes } = this.props
    var productList = this.state.products.map(function(product) {
      return <CatalogItem
                addItem={ this.props.addItem }
                imgSrc={ this.getImg(product.model) }
                model={product.model}
                cost={ this.getPrice(product.model) }
              />
    }, this)

    if (this.state.redirect) {
      return <Redirect push to="/store/checkout" />
    }

    if (this.state.loading){
      return <h1>Loading...</h1>
    }

    return (
      <div>
        <Typography
          className={ classes.productTitle }
          type="subheading"
          variant="display3"
          align="center"
          gutterBottom
        >
          View Products
        </Typography>
        <Card>
          <div className={ classes.root }>
            <GridList cellHeight={ 300 } className={ classes.gridList }>
              { productList }
            </GridList>
          </div>
          <Button
            onClick={ this.routeToCheckout }
            disabled={ this.props.isEmpty }
            variant="raised"
            className={ classes.button }>
              Proceed to Checkout
          </Button>
        </Card>
      </div>
    )
  }
}

export default withStyles(styles)(CatalogPage)
