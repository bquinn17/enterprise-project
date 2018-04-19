# Sales ERP Front-End with React Static & Material UI

## Library References

> [Material UI](https://material-ui-next.com/) is a set of React components that implement Google's Material Design.

> [React](https://reactjs.org/) is a JavaScript library for building user interfaces

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
