import React, { Component } from 'react'
import Express from './Express'
import PropTypes from 'prop-types'
import './Express.css'

class ExpressList extends Component {

  static propTypes = {
    express: PropTypes.array,
    onDeleteExpress: PropTypes.func
  }

    static defaultProps = {
        expresses: []
    }

  handleDeleteExpress (index) {
    if (this.props.onDeleteExpress) {
      this.props.onDeleteExpress(index)
    }
  }

   render() {
    return (
      <div>
        {this.props.expresses.map((express, i) =>
          <Express
            express={express}
            key={i}
            index={i}
            onDeleteExpress={this.handleDeleteExpress.bind(this)} />
        )}
      </div>
    )
  }
}
export default ExpressList
