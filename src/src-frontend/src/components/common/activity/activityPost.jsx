import React, { useContext } from 'react';
import { Link } from 'react-router-dom';

import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../contexts/AuthProvider';

import { CommentIconDark, LikeIconDark } from './components/icon/publicationIcon';
import ProfilePictureCircle from './components/profilePicture/profilePicture';

import './style.scss';

const ActivityPostComponent = () => {
    const { theme, isDark } = useContext(ThemeContext);
    const { userData } = useAuthContext();
    const userList = [userData.user, ...userData.user.friends];
    const profileImage = userData.user.image;
    return (
        <div className='publication-container'>

        </div>
    );
};

export default ActivityPostComponent;
