import React from 'react'
//
import Card from 'material-ui/Card'
import Typography from 'material-ui/Typography'

/**
 * ContactUs is a placeholder page for when the users would leave for
 * the customer support's page.
 *
 * Author: Brendan Jones, bpj1651@rit.edu
 */
class ContactUs extends React.Component {
  render() {
    return(
      <Card>
        <Typography
          type="subheading"
          variant="display3"
          align="center"
          gutterBottom
        >
        For future releases this would go to customer support
        </Typography>
      </Card>
    )
  }
}

export default ContactUs
