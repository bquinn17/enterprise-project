import React, { PureComponent } from 'react'
//
import EmployeeDashboard from './EmployeeDashboard'
import EmployeeLoginPage from './EmployeeLoginPage'
import PageNotFound from '../errors/PageNotFound'
import WholesaleAccountPage from './WholesaleAccountPage'
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
      default:
        pageToShow = <PageNotFound />
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
