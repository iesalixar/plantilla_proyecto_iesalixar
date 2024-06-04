import React, { useContext } from 'react';
import { ThemeContext } from '../../../../../contexts/ThemeProvider';

import { ProfileClear } from '../../../../../assest/icon/sidebarIcons-clear';
import { ProfileDark } from '../../../../../assest/icon/sidebarIcons-dark';

import './style.scss';

const ProfilePictureComponent = ({ source, size }) => {
    const { isDark } = useContext(ThemeContext);

    return (
        <div className="profile-picture-circle" style={{ width: size, height: size, borderRadius: '50%', overflow: 'hidden' }}>
            {source ? (
                <img src={`data:image/jpeg;base64,${source}`} alt="Profile" style={{ width: '100%', height: '100%', objectFit: 'cover' }} />
            ) : (
                isDark ? <ProfileDark /> : <ProfileClear />
            )}
        </div>
    );
};

export default ProfilePictureComponent;
