import React from 'react'
//
import Button from 'material-ui/Button'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import logoImg from '../../logo.jpg'
import styles from './homePageStyles'

/**
 * HomePage represents the landing page for the Sales ERP.
 * Users are given the option to proceed to the store or the employee section
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class HomePage extends React.Component {

  render() {
    const { classes } = this.props
    return (
      <div>
        <Typography
          type="subheading"
          variant="display3"
          align="center"
          gutterBottom
        >
          Welcome to Kenn-U-Ware
        </Typography>
        <Typography
           variant="subheading"
           align="center"
           color="textSecondary"
           gutterBottom
        >
          Wearable devices balancing elegance with functionality
        </Typography>
        <img className={ classes.logo } src={ logoImg } />
        <div align="center">
          <Button
            href="/store/catalog"
          >
            Store
          </Button>
          <Button
            href="/employee/login">
            Employee Login
          </Button>
        </div>
      </div>
    )
  }
}

export default withStyles(styles)(HomePage)
