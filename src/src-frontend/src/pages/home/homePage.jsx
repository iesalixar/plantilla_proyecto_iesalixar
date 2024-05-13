import FooterComponent from '../../components/footer/footer';
import { DefaultHeader } from '../../components/header/headers';
import SidebarComponent from '../../components/sidebar/sidebar';

const HomePage = () => {
    return (
        <><DefaultHeader></DefaultHeader><SidebarComponent></SidebarComponent><FooterComponent></FooterComponent></>
    );
};
export default HomePage;