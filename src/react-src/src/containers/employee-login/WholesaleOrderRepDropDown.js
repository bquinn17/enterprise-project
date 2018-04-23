import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import styles from './WholesaleOrderPageStyles'

/**
 * WholesaleOrderRepDropDown represents the drop down
 * for the sales rep selection on the parent component,
 * WholesaleOrderPage
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class WholesaleOrderRepDropDown extends React.Component {
  constructor(props){
    super(props)

    // Initially not loading, and no reps in array
    this.state = {
      loading: false,
      reps: []
    }
  }

  // Load data to the component once mounted
  componentDidMount() {

    // Show loading wheel
    this.setState({
      loading: true
    })

    // Get the data
    fetch("http://kennuware-1772705765.us-east-1.elb.amazonaws.com/api/employee?position=Sales%20Rep&region=" + this.props.region)
      .then(response => response.json())
      // Update the component with data, and stop loading
      .then(data => this.setState({ reps: data.data, loading: false }))
      .catch(error => console.log("error!!! " + error)
    )
  }

  componentWillReceiveProps(nextProps) {
    if(nextProps.region != this.props.region) {
      // Show loading wheel
      this.setState({
        loading: true
      })

      // Get the data
      fetch("http://kennuware-1772705765.us-east-1.elb.amazonaws.com/api/employee?position=Sales%20Rep&region=" + nextProps.region)
        .then(response => response.json())
        // Update the component with data, and stop loading
        .then(data => this.setState({ reps: data.data, loading: false }))
        .catch(error => console.log("error!!! " + error)
      )
    }
  }

  render() {
    const { classes } = this.props
    if(this.state.loading){
      return <h1>Loading...</h1>
    }
    return(
      <div>
        <Typography
          type="subheading"
          variant="display1"
        >
          Sales Rep
        </Typography>
        <Select
          value={ this.props.selectedValue }
          onChange={ this.props.onSelect }
          className={ classes.region }
        >
          { this.state.reps.map(rep => (
              <MenuItem value={ rep }>
                { rep.firstName + " " + rep.lastName }
              </MenuItem>
          ))}
        </Select>
      </div>
    )
  }
}
export default withStyles(styles)(WholesaleOrderRepDropDown)
