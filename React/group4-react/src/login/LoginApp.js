import React, { Component } from 'react'
import LoginInput from './LoginInput'
import '.index.css'
class LoginApp extends Component {
    constructor () {
        super()
        this.state = {
            phonenumber: '',
            address: ''
        }
    }

    componentWillMount () {
        let login = localStorage.getItem('login')
        login = JSON.parse(login)
        this.setState({ phonenumber:login.phonenumber, address:login.address })
    }

    _saveLogin (login) {
        localStorage.setItem('login', JSON.stringify(login))
    }

    handleSubmitLogin (login) {
        console.log(login)
        if (!login.phonenumber) return alert('请输入手机号')
        const tmp_phonenumber = login.phonenumber
        var len = tmp_phonenumber.length
        var number_right = true
        if(len !== 11)
            number_right = false
        for(var i = 0; i < len; i++){
            if(tmp_phonenumber[i]>'9' || tmp_phonenumber[i]<'0')
                number_right = false
        }
        if(!number_right) return alert("请输入正确的手机号")
        if (!login.address) return alert('请输入地址')
        if(number_right)
            this.setState({ phonenumber:login.phonenumber, address:login.address })
        this._saveLogin(login)
    }

    render() {
        return (
            <div className='wrapper'>
                <LoginInput
                    onSubmit={this.handleSubmitLogin.bind(this)}/>
            </div>
        )
    }
}

export default LoginApp
