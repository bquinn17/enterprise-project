import React from 'react'
//
import { MenuItem } from 'material-ui/Menu'
import Select from 'material-ui/Select'
import Typography from 'material-ui/Typography'
import { withStyles } from 'material-ui/styles'
//
import axios from 'axios'
import styles from './WholesaleOrderPageStyles'
import getRepsFromRegion from '../../stubbed-data/dataFromHR'

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
    this.state = {
      loading: false,
      reps: []
    }
  }
  componentDidMount() {
    this.setState({
      loading: true
    })
    fetch("/api/mocked/hr/salesreps?region=" + this.props.region)
      .then(response => response.json())
      .then(data => this.setState({ reps: data.data, loading: false }))
      .catch(error => console.log("error!!! " + error));
  }
  render() {
    //const reps = getRepsFromRegion(this.props.region)
    const { classes } = this.props
    if(this.state.loading){
      return <h1>Loading...</h1>
    }
    console.log(this.state.reps)
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
              <MenuItem value={ rep.id }>{ rep.firstName + " " + rep.lastName }</MenuItem>
          ))}
        </Select>
      </div>
    )
  }
}
export default withStyles(styles)(WholesaleOrderRepDropDown)
