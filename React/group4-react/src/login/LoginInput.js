import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withRouter } from 'react-router'
import './index.css'
class LoginInput extends Component {
    static contextTypes = {
        router: PropTypes.object
    }

    constructor () {
        super()
        this.state = {
            phonenumber: '',
            address: ''
        }
    }

    handlePhonenumberChange (event) {
        this.setState({
            phonenumber: event.target.value
        })
    }

    handleAddressChange (event) {
        this.setState({
            address: event.target.value
        })
    }

    handleSubmit () {
        if (this.props.onSubmit) {
            const { phonenumber, address } = this.state
            this.props.onSubmit({phonenumber, address})
        }
        this.setState({ phonenumber:'', address: '' })
        this.context.router.history.push('/main')
    }

    render() {
        return (
            <div className='login-input'>
                <div className='login-field'>
                    <span className='login-field-name'>手机号：</span>
                    <div className='login-field-input'>
                        <input
                            value={this.state.phonenumber}
                            onChange={this.handlePhonenumberChange.bind(this)} />
                    </div>
                </div>
                <div className='login-field'>
                    <span className='login-field-name'>地址：</span>
                    <div className='login-field-input'>
                        <textarea
                            value={this.state.address}
                            onChange={this.handleAddressChange.bind(this)} />
                    </div>
                </div>
                <div className='login-field-button'>
                    <button
                        onClick={this.handleSubmit.bind(this)}>
                        登陆
                    </button>
                </div>
            </div>
        )
    }
}

export default withRouter(LoginInput)
