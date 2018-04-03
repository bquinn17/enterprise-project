import React, { PureComponent } from 'react'
//
import CssBaseline from 'material-ui/CssBaseline'
import AppBar from 'material-ui/AppBar'
import Tabs, { Tab } from 'material-ui/Tabs'
import { withStyles } from 'material-ui/styles'
//
import { Switch, Route, Router } from 'react-router'
//
import HomePage from './containers/home/HomePage'
import Employee from './containers/employee-login/Employee'
import Store from './containers/store/Store'
import PageNotFound from './containers/errors/PageNotFound'
//
import createBrowserHistory from 'history/createBrowserHistory'


// Custom styles
const styles = {
  '@global': {
    img: {
      maxWidth: '100%',
    },
  },
  appBar: {
    flexWrap: 'wrap',
  },
  tabs: {
    width: '100%',
  },
  content: {
    padding: '1rem',
  },
}

class App extends PureComponent {
  // Remove the server-side injected CSS.
  componentDidMount () {
    const jssStyles = document.getElementById('jss-server-side')
    if (jssStyles && jssStyles.parentNode) {
      jssStyles.parentNode.removeChild(jssStyles)
    }
  }

  render () {
    const { classes } = this.props
    const history = createBrowserHistory()
    return (
        <div className={classes.container}>
          <CssBaseline />
          <div className={classes.content}>
          <Router history={history}>
            <Switch>
              <Route exact path="/" component={ HomePage }/>
              <Route
                exact
                path="/employee/login"
                render={ (props) => (
                  <Employee employeePage="login" />
                )}
              />
              <Route
                exact
                path="/employee/dashboard"
                render={ (props) => (
                  <Employee employeePage="dashboard" />
                )}
              />
              <Route
                exact
                path="/store/catalog"
                render={ (props) => (
                  <Store storePage="catalog" />
                )}
              />
              <Route
                exact
                path="/store/contact-us"
                render={ (props) => (
                  <Store storePage="contact-us" />
                )}
              />
              <Route
                exact
                path="/store/checkout"
                render={ (props) => (
                  <Store storePage="checkout" />
                )}
              />
              <Route
                exact
                path="/employee/wholesale/order"
                render={ (props) => (
                  <Employee employeePage="order" />
                )}
              />
              <Route
                exact
                path="/employee/wholesale/account"
                render={ (props) => (
                  <Employee employeePage="account" />
                )}
              />
              <Route component={PageNotFound} />
            </Switch>
          </Router>
          </div>
        </div>
    )
  }
}

const AppWithStyles = withStyles(styles)(App)

export default (AppWithStyles)
