import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './WholesaleOrderPageStyles'
import getRepsFromRegion from '../../stubbed-data/dataFromHR'

/**
 * WholesaleOrderRepDropDown represents the drop down
 * for the sales rep selection on the parent component,
 * WholesaleOrderPage
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class WholesaleOrderRepDropDown extends React.Component {
  render() {
    const reps = getRepsFromRegion(this.props.region)
    const { classes } = this.props
    return(
      <div>
        <Typography
          type="subheading"
          variant="display1"
        >
          Sales Rep
        </Typography>
        <Select
          value={ this.props.selectedValue }
          onChange={ this.props.onSelect }
          className={ classes.region }
        >
          { reps.map(rep => (
              <MenuItem value={ rep.id }>{ rep.name }</MenuItem>
          ))}
        </Select>
      </div>
    )
  }
}
export default withStyles(styles)(WholesaleOrderRepDropDown)
