import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './Express.css'

class ExpressInput extends Component {

  static propTypes = {
    onSubmit: PropTypes.func
  }

    constructor () {
        super()
        this.state = {
            username: '',
            content: '',
            phone:''
        }
    }

   componentDidMount () {
     this.textarea.focus()
   }

  componentWillMount () {
    this._loadUsername()
    this._loadPhone()
  }

  _loadUsername () {
    const username = localStorage.getItem('username')
    if (username) {
      this.setState({ username })
    }
  }

 _loadPhone () {
    const phone = localStorage.getItem('phone')
    if (phone) {
      this.setState({ phone })
    }
  }

   _saveUsername (username) {
     localStorage.setItem('username', username)
   }

  _savePhone (phone) {
     localStorage.setItem('phone', phone)
   }


   handleUsernameBlur (event) {
     this._saveUsername(event.target.value)
   }

   handlePhoneBlur (event) {
     this._savePhone(event.target.value)
   }

   handleUsernameChange (event) {
        this.setState({
            username: event.target.value
        })
    }


    handlePhoneChange (event) {
        this.setState({
            phone: event.target.value
        })
    }

    handleContentChange (event) {
        this.setState({
            content: event.target.value
        })
    }



    handleSubmit () {
        if (this.props.onSubmit) {
     	  this.props.onSubmit({
		username: this.state.username,
		phone:this.state.phone,
		content: this.state.content,
		createdTime: +new Date()
	      })
    }
        this.setState({ content: '' })
    }


    render () {
        return (
            <div className='express-input'>
                <div className='express-field'>
                    <span className='express-field-name'>用户名：</span>
                    <div className='express-field-input'>
                         <input
			      value={this.state.username}
			      onBlur={this.handleUsernameBlur.bind(this)}
			      onChange={this.handleUsernameChange.bind(this)} />
                    </div>
                </div>

            <div className='express-field'>
                <span className='express-field-name'>手机号：</span>
            <div className='express-field-input'>
                <input
                 value={this.state.phone}
	  	 onBlur={this.handlePhoneBlur.bind(this)}
		 onChange={this.handlePhoneChange.bind(this)} />
            </div>
            </div>

                <div className='express-field'>
                    <span className='express-field-name'>快递信息：</span>
                    <div className='express-field-input'>
                         <textarea
			      ref={(textarea) => this.textarea = textarea}
			      value={this.state.content}
			      onChange={this.handleContentChange.bind(this)} />
                    </div>
                </div>

                <div className='express-field-button'>
                    <button
                    onClick={this.handleSubmit.bind(this)}>
                    发布
                    </button>
                </div>
            </div>
    )
    }
}

export default ExpressInput
