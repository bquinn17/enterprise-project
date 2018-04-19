import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './WholesaleOrderPageStyles'

/**
 * WholesaleOrderAccountDropDown represents the drop down
 * for the WholesaleAccount selection on the parent component,
 * WholesaleOrderPage
 *
 * Author: Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */
class WholesaleOrderAccountDropDown extends React.Component {
  constructor(props){
    super(props)

    // Initially not loading, and no accounts loaded
    this.state = {
      loading: false,
      accounts: []
    }
  }

  // Load data from API to component once it's mounted
  componentDidMount() {

    // Begin loading
    this.setState({
      loading: true
    })

    // Get the data
    fetch("/api/wholesale/accounts")
      .then(response => response.json())
      // On success, set the data to the accounts array and stop loading
      .then(data => this.setState({ accounts: data, loading: false }))
      .catch(error => console.log("error!!! " + error));
  }
  render() {
    const { classes } = this.props
    if(this.state.loading) {
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
              <MenuItem value={ acc }>{ acc.name }</MenuItem>
          )) }
        </Select>
      </div>
    )
  }
}

export default withStyles(styles)(WholesaleOrderAccountDropDown)
