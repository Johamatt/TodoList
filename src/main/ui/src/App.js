
import './App.css';
import Task from './components/Task';
import Deadline from './components/Deadline';
import Weather from './components/Weather';
import React, { useState, useEffect } from 'react';
import { Layout, Menu} from 'antd';

//import ReactWeather from 'react-open-weather';

//import 'react-open-weather/lib/css/ReactWeather.css';



function App() {
  const { Header, Content, Footer, Sider } = Layout;



  return (

    <Layout>
      <Header  style={{ padding: 0,textAlign: 'center' }}>
      <div className="logo" />
      <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
        <Menu.Item key="1">

        <span>To-do's</span> 

        </Menu.Item>
        <Menu.Item key="2">

          <span>Done</span>

        </Menu.Item>
      </Menu>
  
       
      </Header>
    
      <Layout style={{ minHeight: '100vh' }}>
        
        <Sider style={{padding: 10, backgroundColor: '#fff', minWidth: '100vh', paddingTop: 10}}>
          <Content>
            <Weather />     
          </Content>
        </Sider>

        <Layout className="site-layout">
          
          <Content style={{ margin: '0 16px' }}>
            <div  style={{ padding: 24, minHeight: 360 }}>
              <Task/>
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
      </Layout>
      </Layout>

    
  );
  
}

export default App;
