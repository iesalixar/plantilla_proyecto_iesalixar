import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { CommentIconDark, LikeIconDark } from '../../assest/icons/components/publicationIcon';
import './style.scss';
import example from '../../assest/examples/pexels-anush-gorak-1431282.jpg';
import mockData from '../../data/mocker';
import { ThemeContext } from '../../contexts/theme';
import ProfilePictureCircle from './profilePicture/profilePicture';
import avatar from '../../assest/examples/avatar.png';

const PublicationComponent = () => {
    const participants = ['@user1', '@user2', '@user3', '@user4', '@user5'];
    const { theme, isDark } = useContext(ThemeContext);
    return (
        <div className='publication-container'>
            {mockData.map((data, index) => (
                <div key={index} className='publication'>
                    <div className='publication-header'>
                        <ProfilePictureCircle source={avatar} size={32} />
                        <span id='title' style={{ color: theme.teal12 }}>{data.username}</span>
                    </div>
                    <div className="img-container">
                        <img src={example} alt="" />
                    </div>
                    <div className='options-container'>
                        <div><LikeIconDark className='icon' /></div>
                        <div><CommentIconDark className='icon' /></div>
                    </div>
                    <div className='comment-container'>
                        <span id='title' style={{ color: theme.teal11 }}>{data.username}</span>
                        <span style={{ color: theme.gray11 }}>{`Participando en un evento de ${data.type.toLowerCase()}.`}</span>
                        <ul>
                            <li style={{ color: theme.teal11 }}><strong>Type:</strong> <span style={{ color: theme.gray11 }}>{data.type}</span></li>
                            <li style={{ color: theme.teal11 }}><strong>Duration:</strong> <span style={{ color: theme.gray11 }}>{data.duration}</span></li>
                            <li style={{ color: theme.teal11 }}><strong>Place:</strong> <span style={{ color: theme.gray11 }}>{data.place}</span></li>
                            <li style={{ color: theme.teal11 }}><strong>Participants:</strong> <span style={{ color: theme.gray11 }}>{data.participants.map(participant => (
                                <Link key={participant} to={`/${participant.substring(1)}`} style={{ color: theme.gray12 }}>{participant}</Link>
                            )).reduce((prev, curr) => [prev, ', ', curr])}</span></li>
                        </ul>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default PublicationComponent;
