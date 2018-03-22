import React, { PureComponent } from 'react'
//
import { withRouteData } from 'react-static'
//
import EmployeeLoginPage from './EmployeeLoginPage'
import WholesaleOrderPage from './WholesaleOrderPage'

/**
 * Employee represents a wrapper class
 * for the employee's web pages.
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class Employee extends PureComponent {
  render() {
    /* Determine which child page to render */
    var pageToShow
    switch(this.props.data.employeePage) {
      case 'dashboard':
        console.log("Found dashboard")
        pageToShow = <WholesaleOrderPage />
        break;
      case 'login':
        pageToShow = <EmployeeLoginPage />
        break;
    }
    return (
      <div>
        { pageToShow }
      </div>
    )
  }
}

export default withRouteData(Employee)
