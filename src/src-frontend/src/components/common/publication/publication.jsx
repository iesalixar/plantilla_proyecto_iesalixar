import React, { useContext } from 'react';
import { Link } from 'react-router-dom';

import { ThemeContext } from '../../../contexts/themeContexts';
import { useAuth } from '../../../contexts/userContexts';

import { CommentIconDark, LikeIconDark } from '../../common/icon/publicationIcon';
import ProfilePictureCircle from '../profilePicture/profilePicture';

import './style.scss';
import example from '../../../assest/examples/avatar.png';

const PublicationComponent = () => {
    const { theme, isDark } = useContext(ThemeContext);
    const { userData } = useAuth();
    const userList = [userData.user, ...userData.user.friends];
    const profileImage = userData.user.image;
    return (
        <div className='publication-container'>

        </div>
    );
};

export default PublicationComponent;
