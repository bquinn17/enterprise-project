import React from 'react'
//
import { Link } from 'react-static'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import FormControl from 'material-ui/Form'
import FormControlLabel from 'material-ui/Form'
import IconButton from 'material-ui/IconButton'
import TextField from 'material-ui/Input'
import Typography from 'material-ui/Typography'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import { withStyles } from 'material-ui/styles'
//
import componentStyles from './employeeLoginPageStyles'

/**
 * LoginForm represents the login page the employees of KennUWare Corp Sales ERP
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class LoginForm extends React.Component {

  render(){
    const { classes } = this.props
    return (
      <div>
        <Typography
          type="subheading"
          variant="display1"
          align="center"
          gutterBottom
        >
          Please log in with your KennUWare Corp Credentials
        </Typography>
        <Card className={ classes.card }>
          <Link to="/">
            <IconButton>
              <ArrowBack />
            </IconButton>
          </Link>
          <TextField
            required="true"
            placeholder="User ID"
            className={ classes.textbox }
          />
          <TextField
            required="true"
            type="password"
            placeholder="Password"
            className={ classes.textbox }
          />
          <Button className={ classes.submitButton }>Log in</Button>
        </Card>
      </div>
    )
  }
}

export default withStyles(componentStyles)(LoginForm)
