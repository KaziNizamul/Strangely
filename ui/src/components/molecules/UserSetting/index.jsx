import React, { useState } from 'react';
import _get from 'lodash/get';
import { useSelector, useDispatch } from 'react-redux';
import { message } from 'antd';
import axios from 'axios';
import { updateUserData } from '../../organisms/LoginSignup/data/LoginSingup.actions';
import { EMPTY_OBJECT } from '../../../resources/shared/global.constant';
import Utility from '../../../utils/Utility';
import { loginSignupService } from '../../../services/urls';
import styles from './UserSetting.module.scss';

function UserSetting() {
  const dispatch = useDispatch();
  const userData = useSelector(state => _get(state, 'loginSignupReducer.userData', EMPTY_OBJECT));

  const [username, setUserName] = useState(userData.userName || '');
  const [email, setEmail] = useState(userData.email || '');
  const [phoneNumber, setPhoneNumber] = useState(userData.phone_number || '');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const [firstName, lastName] = (username || '').split(' ');

    const payload = {
      username,
      first_name: firstName,
      last_name: lastName || '',
      email,
      phone_number: phoneNumber,
      areaId: '',
      password: '',
      joiningDate: '',
      resetToken: '',
    };

    try {
      const response = await axios.put(`${loginSignupService.updateProfile}${userData.userName}`, payload, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (Utility.isObjectDefined(response)) {
        const { data = EMPTY_OBJECT } = response || EMPTY_OBJECT;
        dispatch(updateUserData(data));
        message.success('User settings updated successfully');
      }
    } catch (error) {
      message.error('Error while updating user settings');
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.userSettingForm}>
      {/* name */}
      <label htmlFor="name">
        Name:
        <input
          id="name"
          type="text"
          value={username}
          onChange={e => setUserName(e.target.value)}
        />
      </label>
      {/* email */}
      <label htmlFor="email">
        Email:
        <input
          id="email"
          type="email"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />
      </label>
      {/* phone number */}
      <label htmlFor="phoneNumber">
        Phone Number:
        <input
          id="phoneNumber"
          type="tel"
          value={phoneNumber}
          onChange={e => setPhoneNumber(e.target.value)}
        />
      </label>
      {/* submit */}
      <div className={styles.submitBtnContainer}>
        <input
          type="submit"
          value="Submit"
          className={styles.submitBtn}
        />
      </div>
    </form>
  );
}

export default UserSetting;
