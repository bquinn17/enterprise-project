import React from 'react'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import GridList, { GridListTile, GridListTileBar } from 'material-ui/GridList'
import IconButton from 'material-ui/IconButton'
import Subheader from 'material-ui/List/ListSubheader'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import AddCircle from 'material-ui-icons/AddCircle'
//
import styles from './catalogPageStyles'
//
import flexImg from '../../flex.jpg'
import styleImg from '../../style.jpeg'
import activeImg from '../../active.jpg'

/**
 * CatalogPage is where users can see the range of products KennUWare offers
 * They must select one to configure sizing and to purchase
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class CatalogPage extends React.Component {
  constructor(props){
    super(props)

    this.addStyleWatchToCart = this.addStyleWatchToCart.bind(this)
    this.addFlexWatchToCart = this.addFlexWatchToCart.bind(this)
    this.addActiveWatchToCart = this.addActiveWatchToCart.bind(this)
  }

  addFlexWatchToCart() {
    const flexWatchData = {
      "model": "Kenn-U-Flex",
      "refurbished": false,
      "serialNumber": "12345",
      "cost": 49.99,
      "imgSrc": flexImg
    }
    this.props.addItem(flexWatchData)
  }

  addStyleWatchToCart() {
    const styleWatchData = {
      "model": "Kenn-U-Style",
      "refurbished": false,
      "serialNumber": "123124",
      "cost": 129.99,
      "imgSrc": styleImg
    }
    this.props.addItem(styleWatchData)
  }

  addActiveWatchToCart() {
    const activeWatchData = {
      "model": "Kenn-U-Active",
      "refurbished": false,
      "serialNumber": "123124",
      "cost": 69.99,
      "imgSrc": activeImg
    }
    this.props.addItem(activeWatchData)
  }


  render() {
    const { classes } = this.props

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
              <GridListTile className={ classes.imgWrapper }>
                <img src={ activeImg } />
                <GridListTileBar
                  title={"Kenn-U-Active"}
                  subtitle={ <span>Starting at $69.99</span> }
                  actionIcon={
                    <IconButton
                      className={ classes.icon }
                      onClick={ this.addActiveWatchToCart }
                    >
                      <AddCircle />
                    </IconButton>
                  }
                />
              </GridListTile>
              <GridListTile className={ classes.imgWrapper }>
                <img src={ styleImg } />
                <GridListTileBar
                  title={ "Kenn-U-Style" }
                  subtitle={ <span>Starting at $129.99</span> }
                  actionIcon={
                    <IconButton
                      className={ classes.icon }
                      onClick={ this.addStyleWatchToCart }
                    >
                      <AddCircle />
                    </IconButton>
                  }
                />
              </GridListTile>
              <GridListTile className={ classes.imgWrapper }>
                <img src={flexImg} />
                <GridListTileBar
                  title={ "Kenn-U-Flex" }
                  subtitle={ <span>Starting at $49.99</span> }
                  actionIcon={
                    <IconButton
                      className={ classes.icon }
                      onClick=
                      { this.addFlexWatchToCart }
                    >
                      <AddCircle />
                    </IconButton>
                  }
                />
              </GridListTile>
            </GridList>
          </div>
          <Button
            onClick={ this.props.handleSubmit }
            disabled={ this.props.isEmpty }
            variant="raised"
            className={ classes.button }>
              Checkout
          </Button>
        </Card>
      </div>
    )
  }
}

export default withStyles(styles)(CatalogPage)
