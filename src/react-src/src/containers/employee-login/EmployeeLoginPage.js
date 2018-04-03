import React from 'react'
//
import { Link } from 'react-router-dom'
import axios from 'axios'
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
//
import Loader from 'react-loader'
//
import logoImg from '../../logo.jpg'
/**
 * LoginForm represents the login page the employees of KennUWare Corp Sales ERP
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class LoginForm extends React.Component {
  constructor(props) {
    super(props)

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
    console.log("submit")
    this.setState({ loading: true} )

    const request = {
      "username": this.state.userId,
      "password": this.state.password
    }

    axios.post('/api/mocked/hr/login',
      request
    ).then(function(response){
      this.setState({loading:false})
      window.location.assign("/employee/dashboard")
    }.bind(this)).catch(function(error) {
      alert("error!" + error)
    })

    // axios.post('/api/wholesale/account/new',
    //   request
    // ).then(function(response) {
    //   alert("success!" + response)
    // }).catch(function(error) {
    //   alert("error!" + error)
    // })
    // setTimeout(function(){
    //         this.setState({loading:false})
    //         window.location.assign("/employee/dashboard")
    //    }.bind(this),1000)

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
          <img src={ logoImg} className={ classes.logoImg } />
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
             className={ classes.loadWheel}
          >
          </Loader>
        </Card>
      </div>
    )
  }
}

export default withStyles(componentStyles)(LoginForm)
