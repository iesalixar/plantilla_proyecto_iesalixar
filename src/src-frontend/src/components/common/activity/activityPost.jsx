import React, { useContext } from 'react';
import { Link } from 'react-router-dom';

import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../contexts/AuthProvider';
import ProfilePictureComponent from '../../layout/navbar/components/profilePicture/profilePicture';

import './style.scss';

const ActivityPostComponent = ({ activity }) => {
    const { theme, isDark } = useContext(ThemeContext);
    const { userData } = useAuthContext();

    // Copiar actividades creadas por el usuario
    let activities = [...userData.user.createdActivities];

    // Copiar amigos del usuario
    const userFriends = [...userData.user.friends];

    // Agregar actividades creadas por los amigos del usuario
    for (const friend of userFriends) {
        activities = [...activities, ...friend.createdActivities];
    }

    console.log(activities);
    // Función para convertir base64 a URL de imagen
    const base64ToUrl = (base64) => `data:image/jpeg;base64,${base64}`;

    return (
        <>
            {
                activities.map((activity) => (
                    <div className='publication-container' style={{ background: isDark ? theme.darkBackground : theme.lightBackground }}>
                        <div key={activity.id} className='activity-post'>
                            <div className='activity-header'>
                                <ProfilePictureComponent source={activity.creator.image} size="40px" />
                                <Link href={`/profile/${activity.creator.id}`} className='creator-name'>
                                    {activity.creator.name}
                                </Link>
                            </div>
                            <div className='activity-image'>
                                <img src={base64ToUrl(activity.image)} alt={`${activity.type} activity`} />
                            </div>
                            <div className='activity-details'>
                                <h3>{activity.title}</h3>
                                <p>{activity.type}</p>
                                <p>{activity.duration}</p>
                                <p>{activity.place}</p>
                            </div>
                            <div div className='activity-participants'>
                                {activities.participants.map((participant) => (

                                    <Link href={`/profile/${participant.id}`} className='participant-name'>
                                        {participant.name}
                                    </Link>
                                ))
                                }
                            </div>
                        </div>
                    </div >
                ))
            }
        </>
    );
};

export default ActivityPostComponent;
