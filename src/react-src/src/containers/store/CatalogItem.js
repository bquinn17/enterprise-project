import React from 'react'
//
import { GridListTile, GridListTileBar } from 'material-ui/GridList'
import IconButton from 'material-ui/IconButton'
import { withStyles } from 'material-ui/styles'
//
import AddCircle from 'material-ui-icons/AddCircle'
//
import styles from './catalogPageStyles'


class CatalogItem extends React.Component {
  constructor(props) {
    super(props)
    this.addItemToCart = this.addItemToCart.bind(this)
    this.disabled = this.disabled.bind(this)
  }

  disabled() {
    switch(this.props.model) {
      case "Kenn-U-Active":
        return false
      case "Kenn-U-Style":
        return false
      case "Kenn-U-Flex":
        return false
      default:
        return true
    }
  }

  addItemToCart() {
    const activeWatchData = {
      "model": this.props.model,
      "refurbished": false,
      "cost": this.props.cost,
      "imgSrc": this.props.imgSrc
    }
    this.props.addItem(activeWatchData)
  }

  shouldComponentUpdate(nextProps) {
    return false
  }

  render() {
    const { classes } = this.props

    // Only load a tileBar with buttons if the model is recognized
    const tileBar = this.disabled() ? (
      <GridListTileBar
        title={ this.props.model }
      />
    ) : (
      <GridListTileBar
        title={ this.props.model }
        subtitle={ <span>Starting at ${ this.props.cost }</span> }
        actionIcon={
          <IconButton
            className={ classes.icon }
            onClick={ this.addItemToCart }
          >
            <AddCircle />
          </IconButton>
        }
      />
    )
    return(
      <GridListTile className={ classes.imgWrapper }>
        <img src={ this.props.imgSrc } />
        { tileBar }
      </GridListTile>
    )
  }
}

export default withStyles(styles)(CatalogItem)
