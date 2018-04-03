import React from 'react'
//
import axios from 'axios'
//
import Button from 'material-ui/Button'
import Card from 'material-ui/Card'
import IconButton from 'material-ui/IconButton'
import TextField from 'material-ui/Input'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import ArrowBack from 'material-ui-icons/ArrowBack'
//
import Loader from 'react-loader'
//
import { Link } from 'react-router-dom'
//
import logoImg from '../../logo.jpg'
import componentStyles from './employeeLoginPageStyles'

/**
 * LoginForm represents the login page the employees of KennUWare Corp Sales ERP
 * On successful login, user is redirected to the EmployeeDashboard
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class LoginForm extends React.Component {
  constructor(props) {
    super(props)

    // Initially, no userId entered, no password entered, and no loading
    this.state = {
      userId: '',
      password: '',
      loading: false
    }
    this.handleFormChange = this.handleFormChange.bind(this)
  }

  handleFormChange = prop => event => {
    this.setState({ [prop]: event.target.value} )
  }

  handleSubmit() {

    // Begin loading
    this.setState({ loading: true} )

    // Create request
    const request = {
      "username": this.state.userId,
      "password": this.state.password
    }

    // Send Request
    axios.post('/api/mocked/hr/login',
      request
    ).then(function(response){
      // Stop loading
      this.setState({ loading: false })
      // Redirect to new link
      window.location.assign("/employee/dashboard")
    }.bind(this)).catch(function(error) {
      alert("error!" + error)
    })

  }

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
          <img src={ logoImg } className={ classes.logoImg } />
          <TextField
            required="true"
            placeholder="User ID"
            className={ classes.textbox }
            onChange = { this.handleFormChange('userId') }
          />
          <TextField
            required="true"
            type="password"
            placeholder="Password"
            className={ classes.textbox }
            onChange = { this.handleFormChange('password') }
          />
          <Button
            disabled={ this.state.loading }
            className={ classes.submitButton }
            onClick={ this.handleSubmit.bind(this) }
          >
            Log in
          </Button>
          <Loader
             loaded={ !this.state.loading }
             className={ classes.loadWheel }
          >
          </Loader>
        </Card>
      </div>
    )
  }
}

export default withStyles(componentStyles)(LoginForm)
