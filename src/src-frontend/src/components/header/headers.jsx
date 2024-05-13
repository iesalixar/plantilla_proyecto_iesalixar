import './style.scss';
import { LogingButton, RegisterButton } from '../buttons/buttons';
import { useLocation } from 'react-router-dom';
import SvgComponent from '../logo/logofull.jsx';
import Logoicon from '../logo/logoIcon.jsx';
import Logotext from '../logo/logotext.jsx';

const DefaultHeader = () => {
    return (
        <div className="header">
            <div className='logo-container'>
                <Logotext className="logo-svg"></Logotext>
            </div>

        </div>

    );
};
const RegisterHeader = () => {
    const location = useLocation();
    const pathname = location.pathname;
    return (
        <div className="header">
            <div className='logo-container'>
                <SvgComponent></SvgComponent>
            </div>
            <div className='btn-container'>
                {pathname === '/signup' ? <LogingButton /> : <RegisterButton />}
            </div>
        </div>

    );
};

const IconHeader = () => {
    return (
        <div className="header">
            <div className='logo-container'>
                <Logoicon></Logoicon>
            </div>
        </div>

    );
};
export { DefaultHeader, RegisterHeader, IconHeader };