import React from 'react'
//
import Button from 'material-ui/Button'
import Card, { CardHeader } from 'material-ui/Card'
import GridList, { GridListTile } from 'material-ui/GridList'
import IconButton from 'material-ui/IconButton'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
import Description from 'material-ui-icons/Description'
import SupervisorAccount from 'material-ui-icons/SupervisorAccount'
//
import { Redirect } from 'react-router'
//
import { Link } from 'react-router-dom'
//
import styles from './employeeDashboardStyles'

/**
 * EmployeeDashboard either renders a view for authenticated employees
 * to choose their desired page in the Employee section of the website.
 *
 * Author: Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */
class EmployeeDashboard extends React.Component {
  constructor(props) {
    super(props)
    // Initally, no redirects
    this.state = {
      redirectToWholesaleOrder: false,
      redirectToWholesaleAccount: false
    }
    // Handle redirect functions
    this.routeToOrder = this.routeToOrder.bind(this)
    this.routeToAccount = this.routeToAccount.bind(this)
  }

  // Sets the state to redirect to WholesaleOrderPage
  routeToOrder() {
    this.setState({
      redirectToWholesaleOrder: true
    })
  }

  // Sets the state to redirect to WholesaleAccountPage
  routeToAccount() {
    this.setState({
      redirectToWholesaleAccount: true
    })
  }

  render() {
    // Grab styles
    const { classes } = this.props

    // Redirect if state was changed
    if (this.state.redirectToWholesaleOrder) {
      return <Redirect push to="/employee/wholesale/order" />
    }

    // Redirect if state was changed
    if (this.state.redirectToWholesaleAccount) {
      return <Redirect push to="/employee/wholesale/account" />
    }

    return (
      <Card className={ classes.card }>
        <Link to="/">
          <IconButton>
            <ArrowBack />
          </IconButton>
        </Link>
        <CardHeader
          className={ classes.cardTitle }
          title="Welcome to Kenn-U-Ware's Employee Hub"
        />
        <GridList cellHeight={ 120 } cols={ 2 }>
          <GridListTile cols={ 1 }>
            <Button
              className={ classes.optionButton }
              onClick={ this.routeToOrder }
            >
              <Description className={ classes.icon }/>
              Report Wholesale Order
            </Button>
          </GridListTile>
          <GridListTile cols={ 1 }>
            <Button
              className={ classes.optionButton }
              onClick={ this.routeToAccount }
            >
              <SupervisorAccount className={ classes.icon } />
              Manage Wholesale Accounts
            </Button>
          </GridListTile>
        </GridList>
      </Card>
    )
  }
}
export default withStyles(styles)(EmployeeDashboard)
