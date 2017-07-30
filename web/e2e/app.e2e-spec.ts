import { AbcPage } from './app.po';

describe('abc App', () => {
  let page: AbcPage;

  beforeEach(() => {
    page = new AbcPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
