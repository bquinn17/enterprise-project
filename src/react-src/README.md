# Sales ERP Front-End with React Static & Material UI

## Library References

> [Material UI](https://material-ui-next.com/) is a set of React components that implement Google's Material Design.

> [React](https://reactjs.org/) is a JavaScript library for building user interfaces

> [React Static](https://github.com/nozzle/react-static) is a way to build static react code.

>> [API Reference](https://nozzle-react-static.netlify.com/api/)

>> [Sample App](https://github.com/nozzle/react-static/tree/master/examples/material-ui)

## System Requirements

> [Yarn](https://yarnpkg.com/lang/en/docs/install/)

> Some text editor. The developer recommends [Atom](https://atom.io/)


## Setup

> 1. Since all the app's node-packages are not stored in GitHub (rather just listed in package.json) run:

>   ```yarn install```

>   To add all packages.
>
> 2. To run locally run the command:

>    ```yarn start```
>
>    This will start the server at localhost:3000 (or another port if 3000 is taken)

> 3. To build the static content run:

>    ```yarn build```
>
>    This will build to dist/

### About React Static

> All data loading and routes are defined in src/static.config.js
>
> [Config API](https://nozzle-react-static.netlify.com/api#staticconfigjs)

### About Styling & Material UI

> All components using any sort of custom styling need to be wrapped with withStyles() from 'material-ui/styles'
>
> Styles are defined as an object.
>
> Example:
>
> ```
> const styles = {
>   content: {
>     padding: '1rem',
>   },
> }
>
> export default withStyles(styles)(ComponentName)
> ```
>
> Styles are then assigned to JSX elements by setting the component's props
>
> Example:
>
> ```
> class SampleComponent extends React.Component {
>   render() {
>     const classes = { this.props }
>     return(<h1 className={classes.content}>Sample</h1>)
>   }
> }
> ```
