import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './WholesaleOrderPageStyles'

/**
 * WholesaleOrderRegionDropDown represents the drop down
 * for the Region selection on the parent component,
 * WholesaleOrderPage
 *
 * Author: Brendan Jones
 */
class WholesaleOrderRegionDropDown extends React.Component {
  render() {
    const { classes } = this.props
    return (
      <div>
        <Typography
          type="subheading"
          variant="display1"
          gutterBottom
        >
          Region
        </Typography>
          <Select
            value={ this.props.selectedValue }
            onChange={ this.props.handleSelect }
            className={ classes.region }
          >
            <MenuItem value="Northeast">Northeast</MenuItem>
            <MenuItem value="Southeast">Southeast</MenuItem>
            <MenuItem value="Midwest">Midwest</MenuItem>
          </Select>
        </div>
    )
  }
}

export default withStyles(styles)(WholesaleOrderRegionDropDown)
