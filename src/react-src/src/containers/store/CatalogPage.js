import React from 'react'
//
import Card from 'material-ui/Card'
import GridList, { GridListTile, GridListTileBar } from 'material-ui/GridList'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './catalogPageStyles'

/**
 * CatalogPage is where users can see the range of products KennUWare offers
 * They must select one to configure sizing and to purchase
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class CatalogPage extends React.Component {

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
          <GridList cellHeight={ 180 }>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
            <GridListTile className={ classes.productBlock }>
              <h5>Meh</h5>
            </GridListTile>
          </GridList>
        </Card>
      </div>
    )
  }
}

export default withStyles(styles)(CatalogPage)
