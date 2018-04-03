import React from 'react'
import Button from 'material-ui/Button'
import Card, {CardHeader} from 'material-ui/Card'
import { withStyles } from 'material-ui/styles'
import GridList, { GridListTile, GridListTileBar } from 'material-ui/GridList'
import styles from './employeeDashboardStyles'
import Description from 'material-ui-icons/Description'
import SupervisorAccount from 'material-ui-icons/SupervisorAccount'
import { Link } from 'react-router-dom'
import ArrowBack from 'material-ui-icons/ArrowBack'
import IconButton from 'material-ui/IconButton'
import { Redirect } from 'react-router'

class EmployeeDashboard extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      redirectToWholesaleOrder: false,
      redirectToWholesaleAccount: false
    }
    this.routeToOrder = this.routeToOrder.bind(this)
    this.routeToAccount = this.routeToAccount.bind(this)
  }

  routeToOrder() {
    this.setState({
      redirectToWholesaleOrder: true
    })
  }

  routeToAccount() {
    this.setState({
      redirectToWholesaleAccount: true
    })
  }

  render() {
    const { classes } = this.props
    if (this.state.redirectToWholesaleOrder) {
      return <Redirect push to="/employee/wholesale/order" />
    }
    if (this.state.redirectToWholesaleAccount) {
      return <Redirect push to="/employee/wholesale/account" />
    }

    return (
      <Card className={classes.card}>
        <Link to="/">
          <IconButton>
            <ArrowBack />
          </IconButton>
        </Link>
        <CardHeader
          className={classes.cardTitle}
          title="Welcome to Kenn-U-Ware's Employee Hub"
        />
        <GridList cellHeight={120} cols={2}>
          <GridListTile cols={1}>
            <Button className={classes.optionButton} onClick={this.routeToOrder}>
              <Description className={classes.icon}/>
              Report Wholesale Order
            </Button>
          </GridListTile>
          <GridListTile cols={1}>
            <Button className={classes.optionButton} onClick={this.routeToAccount}>
              <SupervisorAccount className={classes.icon} />
              Manage Wholesale Accounts
            </Button>
          </GridListTile>
        </GridList>
      </Card>
    )
  }
}
export default withStyles(styles)(EmployeeDashboard)
