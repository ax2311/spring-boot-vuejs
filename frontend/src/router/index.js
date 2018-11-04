import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import Login from '@/components/Login'
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import Nav from '@/components/Nav'
import Register from '@/components/Register'
import MaintainVendorList from '@/components/MaintainVendorList'
import PublishTender from '@/components/PublishTender'
import CurrentTender from '@/components/CurrentTender'
import BidTender from '@/components/BidTender'
import BidHistory from '@/components/BidHistory'
import Profile from '@/components/Profile'
import ViewProfile from '@/components/ViewProfile'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      components: {
        nav: Nav,
        body: Home,
        footer: Footer
      }
    },
    {
      path: '/callservice',
      name: 'Service',
      components: {
        body: Service,
        footer: Footer
      }
    },
    {
      path: '/bootstrap',
      name: 'Bootstrap',
      components: {
        body: Bootstrap,
        footer: Footer
      }
    },
    {
      path: '/user',
      name: 'User',
      components: {
        body: User,
        footer: Footer
      }
    },
    {
      path: '/login',
      name: 'Login',
      components: {
        nav: Nav,
        body: Login,        
        footer: Footer
      }
    },
    {
      path: '/register',
      name: 'Register',
      components: {
        nav: Nav,
        body: Register,
        footer: Footer
      }
    },
    {
      path: '/maintainVendorList',
      name: 'MaintainVendorList',
      components: {
        nav: Nav,
        body: MaintainVendorList,
        footer: Footer
      }
    },
    {
      path: '/publishTender',
      name: 'PublishTender',
      components: {
        nav: Nav,
        body: PublishTender,
        footer: Footer
      }
    },
    {
      path: '/currentTender',
      name: 'CurrentTender',
      components: {
        nav: Nav,
        body: CurrentTender,
        footer: Footer
      }
    },
    {
      path: '/bidTender',
      name: 'BidTender',
      components: {
        nav: Nav,
        body: BidTender,
        footer: Footer
      }
    },
    {
      path: '/bidHistory',
      name: 'BidHistory',
      components: {
        nav: Nav,
        body: BidHistory,
        footer: Footer
      }
    },
    {
      path: '/profile',
      name: 'Profile',
      components: {
        nav: Nav,
        body: Profile,
        footer: Footer
      }
    },
    {
      path: '/viewProfile',
      name: 'ViewProfile',
      components: {
        nav: Nav,
        body: ViewProfile,
        footer: Footer
      }
    }
  ]
})
