import React from 'react'
//
import Button from 'material-ui/Button'
import Card, { CardContent } from 'material-ui/Card'
import IconButton from 'material-ui/IconButton'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import { Link } from 'react-router-dom'
//
import styles from './wholesaleAccountCreatedStyles'
//
class WholesaleOrderPlaced extends React.Component {
  render() {
    const { classes } = this.props
    return (
      <Card className={ classes.card }>
        <Link to="/employee/dashboard">
          <IconButton>
            <ArrowBack />
          </IconButton>
        </Link>
        <Typography
          type="subheading"
          variant="display2"
          align="center"
          gutterBottom
        >
          Wholesale order placed!
        </Typography>
      </Card>
    )
  }
}
export default withStyles(styles)(WholesaleOrderPlaced)
