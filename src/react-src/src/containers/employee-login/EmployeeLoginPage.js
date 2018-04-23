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
import rs from 'jsrsasign'
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
      error: null,
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
    axios.post('https://api-gateway-343.herokuapp.com/auth/login',
      request
    ).then(function(response){
      if(response.data.status) { // This is true when a valid Kenn-U-Ware employee login occurs

        // For validating the employee does have "Admin" privlidges to do employee actions
        var jwt= response.data.token.split(/\./);
        var payload = rs.KJUR.jws.JWS.readSafeJSONString(rs.b64utoutf8(jwt[1]));
        var userId = payload.id;
        axios.get('http://kennuware-1772705765.us-east-1.elb.amazonaws.com/api/employee?userId=' + userId).then(function(response) {
          console.log(response.data.roleName)
          if(response.data.roleName === "Admin") {
            this.setState({ loading: false, error: null })
            window.location.assign("/employee/dashboard")
          } else {
            this.setState({ loading: false, error: "Insufficient permissions to access this page. Must be an admin role", userId: '', password: ''})
          }
        }.bind(this)).catch(function(error) {
          alert("error!" + error)
        })

      } else {
        this.setState({ loading: false, error: response.data.message, userId: '', password: ''})
      }
      // Stop loading

      // Redirect to new link
      //
    }.bind(this)).catch(function(error) {
      alert("error!" + error)
    })

  }

  render(){
    const { classes } = this.props
    var errorBox = this.state.error === null ? null : (
      <Typography
        type="subheading"
        variant="headline"
        align="center"
        gutterBottom
      >
        Unable to login: { this.state.error }
      </Typography>
    )
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
          { errorBox }
          <TextField
            required="true"
            placeholder="User ID"
            className={ classes.textbox }
            value ={ this.state.userId }
            onChange = { this.handleFormChange('userId') }
          />
          <TextField
            required="true"
            type="password"
            placeholder="Password"
            className={ classes.textbox }
            value={ this.state.password }
            onChange = { this.handleFormChange('password') }
          />
          <Button
            disabled={ this.state.loading || (this.state.userId.length <= 0 || this.state.password.length <= 0) }
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
