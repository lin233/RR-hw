// import React, { Component } from 'react'
// import ReactDOM from 'react-dom'
// import './index.css'
//
// const users = [
//     { phonenumber: '12345678901', address: '123' },
//     { phonenumber: '22222222222', address: '222' },
//     { phonenumber: '23333333333', address: '85862' },
//     { phonenumber: '83467426785', address: '4as5' }
// ]
//
// class LoginButton extends Component {
//     constructor () {
//         super()
//         this.state = { isLogin: false }
//     }
//
//     handleClickOnLoginButton () {
//         this.setState({
//             isLogin: !this.state.isLogin
//         })
//     }
//
//     render () {
//         return (
//             <button onClick={this.handleClickOnLoginButton.bind(this)}>
//                 {this.state.isLogin ? '退出' : '登陆'}
//             </button>
//         )
//     }
// }
//
// class User extends Component {
//     render () {
//         const { user } = this.props
//         return (
//             <div>
//                 <div>姓名：{user.phonenumber}</div>
//                 <hr />
//             </div>
//         )
//     }
// }
//
// class Index extends Component {
//     render () {
//         return (
//             <div>
//                 <LoginButton/>
//                 {users.map((user, i) => <User key={i} user={user} />)}
//             </div>
//         )
//     }
// }
//
// ReactDOM.render(
//     <Index />,
//     document.getElementById('root')
// )

import React from 'react'
import ReactDOM from 'react-dom'
import LoginApp from './LoginApp'
import './index.css'

ReactDOM.render(
    <LoginApp />,
    document.getElementById('root')
)