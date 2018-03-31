import React from 'react'
import ReactDOM from 'react-dom'
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles'

// Your top level component
import App from './App'

// Your Material UI Custom theme
import theme from './theme'

// Render your app
if (typeof document !== 'undefined') {
  const renderMethod = module.hot ? ReactDOM.render : ReactDOM.hydrate
  const muiTheme = createMuiTheme(theme)

  const render = Comp => {
    renderMethod(
      <MuiThemeProvider theme={muiTheme}>
        <Comp />
      </MuiThemeProvider>,
      document.getElementById('root')
    )
  }

  // Render!
  render(App)
}
