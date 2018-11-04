import { shallowMount } from '@vue/test-utils';
//import Hello from '@/components/Hello'
import Home from '@/components/Home'

describe('Home.vue', () => {
  it('should render correct hello message', () => {
    // Given
    const homewrapped = shallowMount(Home, {
      // see https://stackoverflow.com/a/37940045/4964553
      propsData: { hellomsg: 'Welcome' },
      // see https://vue-test-utils.vuejs.org/guides/using-with-vue-router.html#testing-components-that-use-router-link-or-router-view
      stubs: ['router-link', 'router-view']
    });

    // When
    const contentH1 = homewrapped.find('h1');

    // Then
    expect(contentH1.text()).toEqual('Welcome');
  })
})
