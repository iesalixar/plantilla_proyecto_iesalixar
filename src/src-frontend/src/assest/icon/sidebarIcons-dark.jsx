import * as React from "react"
import './style.scss'

const CreateDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#84e7ed"
            d="M11 17h2v-4h4v-2h-4V7h-2v4H7v2h4zm-6 4q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h14q.825 0 1.413.588T21 5v14q0 .825-.587 1.413T19 21z"
        />
    </svg>
)

const HomeDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#84e7ed"
            d="M4 14.85V11q0-.2.075-.375T4.3 10.3l7-7q.15-.15.325-.225Q11.8 3 12 3t.375.075q.175.075.325.225l1.425 1.4ZM17 20v-8.175L13.075 7.9 15.2 5.775l4.5 4.525q.125.15.213.325Q20 10.8 20 11v8q0 .425-.288.712Q19.425 20 19 20ZM5 20q-.425 0-.713-.288Q4 19.425 4 19v-2.05l3-3V17h8.5v3Z"
        />
    </svg>
)
const LogoutDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 -0.9956 24 24"
        {...props}
    >
        <path
            fill="#84e7ed"
            d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h7v2H5v14h7v2zm11-4-1.375-1.45 2.55-2.55H9v-2h8.175l-2.55-2.55L16 7l5 5z"
        />
    </svg>
)

const NotificationDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 20 20"
        {...props}
    >
        <path
            fill="#84e7ed"
            fillRule="evenodd"
            d="M3.172 5.172a4 4 0 0 1 5.656 0L10 6.343l1.172-1.171a4 4 0 1 1 5.656 5.656L10 17.657l-6.828-6.829a4 4 0 0 1 0-5.656"
            clipRule="evenodd"
        />
    </svg>
)
const ProfileDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={32}
        height={32}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#7adde3"
            d="m19.6 21-6.3-6.3q-.75.6-1.725.95T9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l6.3 6.3zM9.5 14q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5 6.313 6.313 5 9.5t1.313 3.188T9.5 14"
        />
    </svg>
)

const SearchDark = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#84e7ed"
            d="m19.6 21-6.3-6.3q-.75.6-1.725.95T9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l6.3 6.3zM9.5 14q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5 6.313 6.313 5 9.5t1.313 3.188T9.5 14"
        />
    </svg>
)

export { CreateDark, HomeDark, LogoutDark, NotificationDark, ProfileDark, SearchDark };


