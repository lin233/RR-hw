import React, { Component } from 'react';
import PropTypes from 'prop-types'
import './Contact.css';

class Contact extends Component {

  static contextTypes = {
      router: PropTypes.object
  }

  constructor () {
      super()
      this.state = {
          expresses: [],
          show: false
      }
  }

  ConfirmTake () {
    if (this.state.show === false){
      this.setState((previousState) => {
              return ({
                  show: !previousState.show,
              })
          });
    }
    else{
      alert("请联系该同学或点击返回看看别的哦>.<")
    }
  }

  back (){
    this.context.router.history.go(-1);
  }

  render () {
    return (
      <div className='OrderTake'>
        <header className='Take-header'>
          <h1 className='Take-title'>Thanks for taking express</h1>
        </header>
        {/*pretend to have user, need to be fetched from main page*/}
        <div className='chosenOrder'>
          <b className='OrderName'>
            姓名：{this.props.location.query.username}
          </b>
          <br/>
          <b className='OrderDetail'>
            快递详情：{this.props.location.query.content}
          </b>
        </div>
        <div className='Button-field'>
          <button
            className='TakeButton'
            onClick={this.ConfirmTake.bind(this)}>
            确认
          </button>
          <button
            className='TakeButton'
            onClick={this.back.bind(this)}>
            返回
          </button>
        </div>
        <div className='contact-field'>
          {this.state.show === true ? (
            <b className='contactDetail'>
            请联系手机号为{this.props.location.query.phone}的{this.props.location.query.username}同学获取提货码
            </b>) : (null)
          }
        </div>
      </div>
    )
  }
}

export default Contact;
