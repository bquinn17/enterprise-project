import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './WholesaleOrderPageStyles'
import getWholeSaleAccounts from '../../stubbed-data/dataFromFutureReleases'

/**
 * WholesaleOrderAccountDropDown represents the drop down
 * for the WholesaleAccount selection on the parent component,
 * WholesaleOrderPage
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class WholesaleOrderAccountDropDown extends React.Component {
  render() {
    const accounts = getWholeSaleAccounts()
    const { classes } = this.props
    return (
      <div>
        <Typography
          type="subheading"
          variant="display1"
        >
          Wholesale Account
        </Typography>
        <Select
          value={ this.props.selectedValue }
          onChange={ this.props.onSelect }
          className={ classes.region }
        >
          { accounts.map(acc => (
              <MenuItem value={ acc.email }>{ acc.name }</MenuItem>
          )) }
        </Select>
      </div>
    )
  }
}

export default withStyles(styles)(WholesaleOrderAccountDropDown)
