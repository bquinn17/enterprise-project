import React, { PureComponent } from 'react'
//
import EmployeeLoginPage from './EmployeeLoginPage'
import WholesaleOrderPage from './WholesaleOrderPage'
import EmployeeDashboard from './EmployeeDashboard'
import WholesaleAccountPage from './WholesaleAccountPage'
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
    switch(this.props.employeePage) {
      case 'dashboard':
        pageToShow = <EmployeeDashboard />
        break;
      case 'login':
        pageToShow = <EmployeeLoginPage />
        break;
      case 'order':
        pageToShow = <WholesaleOrderPage />
        break;
      case 'account':
        pageToShow = <WholesaleAccountPage />
        break;
    }
    return (
      <div>
        { pageToShow }
      </div>
    )
  }
}

export default Employee
