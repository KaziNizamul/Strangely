/* eslint-disable jsx-a11y/control-has-associated-label */
/* eslint-disable no-console */
import React, { useEffect, useRef, useState } from 'react';
import {
  ChatEngine,
  getOrCreateChat,
} from 'react-chat-engine';
import {
  PlusOutlined,
  SearchOutlined,
} from '@ant-design/icons';
import { useSelector } from 'react-redux';
/* hoc */
import withNavbar from '../../resources/shared/hoc/navbar';
/* styles */
import styles from './Chat.module.scss';
/* service */
import { createGroupChat, getOrCreateMeAsUser } from './service/Chat.service';

function Chat() {
  const chatRef = useRef();
  const [username, setUsername] = useState('');
  const [groupChatTitle, setGroupChatTitle] = useState('');
  const [isInputVisible, setIsInputVisible] = useState(false);
  const userData = useSelector(state => state.loginSignupReducer.userData);

  useEffect(() => {
    getOrCreateMeAsUser({
      userData,
    });
    if (chatRef.current) {
      chatRef.current.handleChatUpdate();
    }
  }, []);

  const handlePlusButtonClick = () => {
    setIsInputVisible(true);
  };

  const handleInputBlur = () => {
    setIsInputVisible(false);
  };

  const handleInputKeyPress = (event) => {
    if (event.key === 'Enter') {
      onHandleCreateGroupChat();
    }
  };

  const createDirectChat = (creds) => {
    getOrCreateChat(
      creds,
      { is_direct_chat: true, usernames: [username] },
      () => {
        setUsername('');
      },
    );
  };

  const onHandleCreateGroupChat = () => {
    if (groupChatTitle.trim() !== '') {
      createGroupChat({
        title: groupChatTitle,
        userData,
      });
      setGroupChatTitle('');
      setIsInputVisible(false);
    }
  };

  const handleDocumentClick = (event) => {
    if (
      !event.target.closest(`.${styles.groupChatCreateContainer}`)
      && !event.target.closest(`.${styles.directMsgChatContainer}`)
    ) {
      setIsInputVisible(false);
    }
  };

  useEffect(() => {
    document.addEventListener('mousedown', handleDocumentClick);
    return () => {
      document.removeEventListener('mousedown', handleDocumentClick);
    };
  }, []);

  const renderChatForm = creds => (
    <div className={styles.renderChatFormContainer}>
      <div className={styles.groupChatCreateContainer}>
        {!isInputVisible ? (
          <div className={styles.createNewGroupContainer}>
            <span className={styles.createGroupChatText}>
              My Chats
            </span>
            <div>
              <button
                type="button"
                tabIndex="0"
                onClick={handlePlusButtonClick}
                className={styles.createGrpChatBtn}
              >
                <PlusOutlined />
              </button>
            </div>
          </div>
        ) : null}

        {isInputVisible && (
          <div className={styles.newGroupChatTitle}>
            <input
              type="text"
              onBlur={handleInputBlur}
              placeholder="Group Chat Title"
              onKeyPress={handleInputKeyPress}
              onChange={e => setGroupChatTitle(e?.target?.value)}
            />
          </div>
        )}
      </div>
      <div className={styles.directMsgChatContainer}>
        <div className={styles.inputContainer}>
          <input
            placeholder="Direct Message (Username)"
            value={username}
            onChange={e => setUsername(e.target.value)}
          />
        </div>
        <button
          type="button"
          tabIndex="0"
          onClick={() => createDirectChat(creds)}
          className={styles.searchButton}
        >
          <SearchOutlined />
        </button>
      </div>
    </div>
  );

  return (
    <div className={styles.chatContainer}>
      <ChatEngine
        ref={chatRef}
        offset={-4}
        projectID={import.meta.env.VITE_CHAT_PROJECT_ID}
        userName={userData?.userName || ''}
        userSecret={userData?.phone_number || ''}
        renderNewChatForm={creds => renderChatForm(creds)}
      />
    </div>
  );
}

export default withNavbar(Chat);
