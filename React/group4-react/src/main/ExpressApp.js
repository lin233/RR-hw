import React, { Component } from 'react'
import ExpressInput from './ExpressInput'
import ExpressList from './ExpressList'
import './Express.css'

class ExpressApp extends Component {
    constructor () {
        super()
        this.state = {
            expresses: []
        }
    }

  componentWillMount () {
    this._loadExpresses()
  }

  _loadExpresses () {
    let expresses = localStorage.getItem('expresses')
    if (expresses) {
      expresses = JSON.parse(expresses)
      this.setState({ expresses })
    }
  }

  _saveExpresses (expresses) {
    localStorage.setItem('expresses', JSON.stringify(expresses))
  }


    handleSubmitExpress (express) {
        if (!express) return
        if (!express.username) return alert('请输入用户名')
        if (!express.content) return alert('请输入快递信息')
        if (!express.phone) return alert('请输入手机号码')

        const expresses = this.state.expresses
	expresses.push(express)
	this.setState({ expresses })
	this._saveExpresses(expresses)
    }

  handleDeleteExpress (index) {
    const expresses = this.state.expresses
    expresses.splice(index, 1)
    this.setState({ expresses })
    this._saveExpresses(expresses)
  }

  render() {
        return (
            <div className='wrapper'>
            <ExpressInput onSubmit={this.handleSubmitExpress.bind(this)} />
        <ExpressList
	  expresses={this.state.expresses}
          onDeleteExpress={this.handleDeleteExpress.bind(this)} />
        </div>
    )
    }
}

export default ExpressApp
