import axios from 'axios'
import React, { Component } from 'react'
import { SheetsRegistry } from 'react-jss/lib/jss'
import JssProvider from 'react-jss/lib/JssProvider'
import { MuiThemeProvider, createMuiTheme, createGenerateClassName } from 'material-ui/styles'

// Your Materil UI Custom theme
import theme from './src/theme'

export default {
  getSiteData: () => ({
    title: 'React Static',
  }),
  getRoutes: async () => {
    const { data: posts } = await axios.get('https://jsonplaceholder.typicode.com/posts')
    return [
      {
        path: '/',
        component: 'src/containers/home/HomePage',
      },
      {
        path: '/employee/login',
        component: 'src/containers/employee-login/Employee',
        getData: () => ({
          data: {"employeePage": "login"},
        }),
      },
      {
        path: '/employee/dashboard',
        component: 'src/containers/employee-login/Employee',
        getData: () => ({
          data: {"employeePage": "dashboard"},
        }),
      },
      {
        path: '/store/catalog',
        component: 'src/containers/store/Store',
        getData: () => ({
          data: {"storePage": "catalog"},
        }),
      },
      {
        path: '/store/contact-us',
        component: 'src/containers/store/Store',
        getData: () => ({
          data: {"storePage": "contact-us"},
        }),
      },
      {
        is404: true,
        component: 'src/containers/errors/PageNotFound',
      },
    ]
  },
  renderToHtml: (render, Comp, meta) => {
    // Create a sheetsRegistry instance.
    const sheetsRegistry = new SheetsRegistry()

    // Create a MUI theme instance.
    const muiTheme = createMuiTheme(theme)

    const generateClassName = createGenerateClassName()

    const html = render (
      <JssProvider registry={sheetsRegistry} generateClassName={generateClassName}>
        <MuiThemeProvider theme={muiTheme} sheetsManager={new Map()}>
          <Comp />
        </MuiThemeProvider>
      </JssProvider>,
    )

    meta.jssStyles = sheetsRegistry.toString()

    return html
  },
  Document: class CustomHtml extends Component {
    render () {
      const { Html, Head, Body, children, renderMeta } = this.props

      return (
        <Html>
          <Head>
            <meta charSet="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet" />
          </Head>
          <Body>
            {children}
            <style id="jss-server-side">{renderMeta.jssStyles}</style>
          </Body>
        </Html>
      )
    }
  },
}
