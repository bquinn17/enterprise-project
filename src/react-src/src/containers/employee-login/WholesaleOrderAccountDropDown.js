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
  constructor(props){
    super(props)
    this.state = {
      loading: false,
      accounts: []
    }
  }

  componentDidMount() {
    this.setState({
      loading: true
    })
    fetch("/api/wholesale/accounts")
      .then(response => response.json())
      .then(data => this.setState({ accounts: data, loading: false }))
      .catch(error => console.log("error!!! " + error));
  }
  render() {
    const accounts = getWholeSaleAccounts()
    const { classes } = this.props
    if(this.state.loading){
      return <h1>Loading...</h1>
    }
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
          { this.state.accounts.map(acc => (
              <MenuItem value={ acc.email }>{ acc.name }</MenuItem>
          )) }
        </Select>
      </div>
    )
  }
}

export default withStyles(styles)(WholesaleOrderAccountDropDown)
