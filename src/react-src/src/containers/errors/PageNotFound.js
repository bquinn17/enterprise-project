import React from 'react'
//
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import errorImg from '../../404.png'
import styles from './pageNotFoundStyles'

/**
 * PageNotFound is rendered whenever a user hits a route that doesn't exist
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class PageNotFound extends React.Component {
  render() {
    const { classes } = this.props
    return (
      <div>
        <Typography variant="headline" align="center">
          404 - Oh no's! We couldn't find that page :(
        </Typography>
        <img src={ errorImg } className={ classes.errorImg} />
      </div>
    )
  }
}

export default withStyles(styles)(PageNotFound)
